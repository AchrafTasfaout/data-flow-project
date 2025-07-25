# Streamlit Fraud Alerts Dashboard

## Overview

This app displays real-time fraud alerts from the `fraud-alerts` Kafka topic using Streamlit.

## Setup & Usage

1. Install dependencies:

   ```
   pip install -r requirements.txt
   ```

2. Make sure Kafka is running and the `fraud-alerts` topic is being populated.

3. Start the dashboard:

   ```
   streamlit run app.py
   ```

4. Open the provided URL in your browser to view live alerts.

---

For more details, see [`app.py`](app.py).
