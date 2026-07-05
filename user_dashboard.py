import json
import os

USER_FILE = "users.json"

def load_users():
    if not os.path.exists(USER_FILE):
        return {}
    with open(USER_FILE, "r") as f:
        try:
            return json.load(f)
        except json.JSONDecodeError:
            return {}

def show_dashboard():
    print("=== USER DASHBOARD ===")
    username = input("Enter your username to view dashboard: ").strip()

    users = load_users()
    if username not in users:
        print("User not found! Please signup first.\n")
        return

    print(f"\nWelcome to your dashboard, {username}!")
    print("Here you can manage your account and view your activities.")
    print("1. View Profile Information")
    print("2. Recent Activity Logs")
    print("3. Account Settings")
    
    choice = input("Select an option (1-3): ").strip()
    if choice == '1':
        print(f"\nProfile Info:\nUsername: {username}\nStatus: Active User")
    elif choice == '2':
        print("\nRecent Activity:\n- Logged in recently.\n- Password updated (if applicable).")
    elif choice == '3':
        print("\nAccount Settings:\n- Change Password (use password_reset.py)\n- Delete Account (Feature coming soon!)")
    else:
        print("\nInvalid choice. Returning to main menu.")
    
    print("\nThank you for using the system!\n")

if __name__ == "__main__":
    show_dashboard()
