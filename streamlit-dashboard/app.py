import streamlit as st
from kafka import KafkaConsumer
import json
import pandas as pd

st.set_page_config(page_title="Fraud Alerts Dashboard", layout="wide")
st.title("🚨 Real-Time Fraud Alerts Dashboard")

KAFKA_BROKER = "localhost:9092"
TOPIC = "fraud-alerts"


@st.cache_resource
def get_consumer():
    return KafkaConsumer(
        TOPIC,
        bootstrap_servers=KAFKA_BROKER,
        auto_offset_reset="latest",
        enable_auto_commit=True,
        group_id="streamlit-dashboard",
        value_deserializer=lambda m: json.loads(m.decode("utf-8")),
    )


consumer = get_consumer()

placeholder = st.empty()
alerts = []

for message in consumer:
    alert = message.value
    alerts.append(alert)

    # Group alerts by fraud_type
    alerts_by_type = {}
    for alert_item in alerts:
        fraud_type = alert_item.get("fraud_type", "Unknown")
        alerts_by_type.setdefault(fraud_type, []).append(alert_item)

    with placeholder.container():
        st.info("🟢 Listening for fraud alerts...")
        # Display total number of alerts
        st.metric("Total Alerts", len(alerts))
        # Counters for each fraud type
        if alerts_by_type:
            fraud_type_icons = {
                "HighValueFraud": "💰",
                "FrequentUserFraud": "🔁",
                "MultiCountryFraud": "🌍",
                "MultiMethodFraud": "💳",
            }
            cols = st.columns(len(alerts_by_type))
            for idx, (ftype, items) in enumerate(alerts_by_type.items()):
                icon = fraud_type_icons.get(ftype, "⚠️")
                cols[idx].metric(f"{icon} {ftype}", len(items))
        # Display latest alerts
        st.markdown("### Latest Fraud Alerts (by Type)")
        if alerts_by_type:
            tabs = st.tabs(
                [
                    f"{fraud_type_icons.get(ftype, '⚠️')} {ftype} ({len(items)})"
                    for ftype, items in alerts_by_type.items()
                ]
            )
            for idx, (fraud_type, items) in enumerate(alerts_by_type.items()):
                with tabs[idx]:
                    df = pd.DataFrame(items)
                    if not df.empty:
                        st.dataframe(df[::-1], use_container_width=True)
                    else:
                        st.info("No alerts for this type yet.")
        else:
            st.info("No alerts received yet.")
