import json
import os

BORROW_FILE = "borrowed_items.json"

def load_borrowed_data():
    if not os.path.exists(BORROW_FILE):
        return {}
    with open(BORROW_FILE, "r") as f:
        try:
            return json.load(f)
        except json.JSONDecodeError:
            return {}

def save_borrowed_data(data):
    with open(BORROW_FILE, "w") as f:
        json.dump(data, f, indent=4)

def borrow_menu(username):
    """Main dashboard for borrowing items once logged in."""
    while True:
        print(f"=== BORROW SYSTEM (Logged in as: {username}) ===")
        print("1. Borrow an Item")
        print("2. View My Borrowed Items")
        print("3. Return an Item")
        print("4. Logout")
        
        choice = input("Choose an option (1-4): ").strip()
        data = load_borrowed_data()

        # Ensure the user has an entry in the system
        if username not in data:
            data[username] = []

        if choice == "1":
            item = input("Enter the name of the item to borrow: ").strip()
            if item:
                data[username].append(item)
                save_borrowed_data(data)
                print(f"Successfully borrowed '{item}'!\n")
            else:
                print("Item name cannot be empty.\n")

        elif choice == "2":
            items = data.get(username, [])
            if items:
                print("\nYour current borrowed items:")
                for i, item in enumerate(items, 1):
                    print(f"  {i}. {item}")
                print()
            else:
                print("You haven't borrowed any items yet.\n")

        elif choice == "3":
            items = data.get(username, [])
            if not items:
                print("You have no items to return.\n")
                continue
                
            print("\nSelect an item to return:")
            for i, item in enumerate(items, 1):
                print(f"  {i}. {item}")
            
            try:
                item_idx = int(input("Enter item number: ")) - 1
                if 0 <= item_idx < len(items):
                    removed_item = items.pop(item_idx)
                    data[username] = items
                    save_borrowed_data(data)
                    print(f"Successfully returned '{removed_item}'!\n")
                else:
                    print("Invalid selection.\n")
            except ValueError:
                print("Please enter a valid number.\n")

        elif choice == "4":
            print("Logging out of borrow system...\n")
            break
        else:
            print("Invalid choice, please try again.\n")
#comment
if __name__ == "__main__":
    # For testing independently, it prompts for a name
    test_user = input("Enter test username to simulate login: ")
    borrow_menu(test_user)
