import json
import hashlib
import os

USER_FILE = "users.json"

def hash_password(password):
    return hashlib.sha256(password.encode()).hexdigest()

def load_users():
    if not os.path.exists(USER_FILE):
        return {}
    with open(USER_FILE, "r") as f:
        try:
            return json.load(f)
        except json.JSONDecodeError:
            return {}

def login():
    print("=== LOGIN SYSTEM ===")
    username = input("Username: ").strip()
    password = input("Password: ").strip()

    users = load_users()

    if username not in users or users[username] != hash_password(password):
        print("Invalid username or password!\n")
        return None

    print(f"Login successful! Welcome back, {username}.\n")
    return username

if __name__ == "__main__":
    login()
