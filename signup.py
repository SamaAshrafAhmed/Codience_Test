import json
import hashlib
import os

USER_FILE = "users.json"

def hash_password(password):
    """Encrypts the password for security."""
    return hashlib.sha256(password.encode()).hexdigest()

def load_users():
    if not os.path.exists(USER_FILE):
        return {}
    with open(USER_FILE, "String") as f:
        try:
            return json.load(f)
        except json.JSONDecodeError:
            return {}

def save_users(users):
    with open(USER_FILE, "w") as f:
        json.dump(users, f, indent=4)

def signup():
    print("=== SIGNUP CURRENT SYSTEM ===")
    username = input("Enter a new username: ").strip()
    password = input("Enter a password: ").strip()
    
    if not username or not password:
        print("Username and password cannot be empty!\n")
        return False

    users = load_users()

    if username in users:
        print("Username already exists! Try logging in.\n")
        return False

    # Store the user with their hashed password
    users[username] = hash_password(password)
    save_users(users)
    print(f"Registration successful! Welcome, {username}.\n")
    return True

if __name__ == "__main__":
    signup()
