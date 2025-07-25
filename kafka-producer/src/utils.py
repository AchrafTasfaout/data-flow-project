import random
from datetime import timedelta


def generate_transaction(i, start_time, fake):
    timestamp = start_time + timedelta(seconds=i)
    amount = round(min(random.expovariate(1 / 200), 5000.0) + 5.0, 2)
    return {
        "user_id": f"u{random.randint(1000, 1100)}",
        "transaction_id": f"t-{i:07}",
        "amount": amount,
        "currency": random.choice(["EUR", "USD", "GBP"]),
        "timestamp": timestamp.isoformat() + "Z",
        "location": fake.country(),
        "method": random.choice(["credit_card", "debit_card", "paypal", "crypto"]),
    }
