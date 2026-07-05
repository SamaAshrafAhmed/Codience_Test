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

def save_users(users):
    with open(USER_FILE, "w") as f:
        json.dump(users, f, indent=4)

def reset_password():
    print("=== PASSWORD RESET ===")
    username = input("Username: ").strip()

    users = load_users()
    if username not in users:
        print("Username not found!\n")
        return

    old_password = input("Current Password: ").strip()
    if users[username] != hash_password(old_password):
        print("Incorrect current password!\n")
        return

    new_password = input("New Password: ").strip()
    if len(new_password) < 6:
        print("Password must be at least 6 characters long.\n")
        return

    users[username] = hash_password(new_password)
    save_users(users)
    print("Password reset successfully!\n")

if __name__ == "__main__":
    reset_password()
