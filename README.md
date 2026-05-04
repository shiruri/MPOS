# 🛒 MPOS — Point of Sale System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java_Swing-007396?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Ant](https://img.shields.io/badge/Apache_Ant-A81C7D?style=for-the-badge&logo=apache&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

**MPOS** is a Java Swing desktop Point of Sale application backed by MySQL. It supports a full admin dashboard, multi-role employee access, inventory and supplier management, sales reporting, encrypted PDF invoice generation, and a complete POS terminal for processing transactions.

---

## ✨ Features

### 🔐 Authentication & Roles
- Employee login using ID and password, verified against the database
- On successful login, the user selects which registered machine to operate
- Three role levels with tiered access:

| Role | Dashboard Access | CRUD | POS Terminal |
|---|---|---|---|
| Admin | Full | Yes | Yes |
| Staff | Limited | Limited | Yes |
| Employee | None | No | Yes |

---

### 📊 Dashboard Modules (Admin / Staff)

**Transactions**
- View all transactions made across POS terminals
- Search by Order ID, Employee ID, Employee Username, Product Quantity, Device Name, Payment Method, or Status
- Delete transactions by date

**Reports**
- View aggregated sales data including total sales count, successful sales, voided sales, total revenue, and total cost
- Revenue is calculated as Total Amount − Total Cost
- Export an encrypted PDF invoice of selected transaction summaries (password = `CurrentUserName + MachineName`)
- Open the output folder directly to browse Receipts and Invoices

**Items**
- Add, update, and delete inventory items
- Item fields: Product ID, Name, Default Quantity, Unit Price, Unit Cost, Category, Supplier, and Description

**Suppliers**
- Add, update, and delete suppliers
- Supplier fields: Supplier ID, Name, Email, and Item Count

**Staff & Admin**
- Add, update, and delete employee accounts
- Fields: Employee Name, Password (used for login verification)

**POS Machines**
- Register and manage POS machines
- Machine fields: Machine ID, Name, Location, Type (`Computer` or `Cash Register`), and Status
- Computer type is restricted to Admin & Staff; Cash Register is used by Employees

**History**
- View a full audit log of all user actions
- Admin-only: clear history requires password confirmation

---

### 🖥️ POS Terminal

**Terminal Panel**
- Displays current active orders
- **Process** — calculates total price with optional discount and tax (selectable via dropdown); opens the Payment Panel if orders are not voided
  - Payment Panel: select payment method, enter amount received, calculate change, and optionally print a receipt (saved to the Receipts folder)
- **Void** — voids the current order and updates the transaction record
- **Transactions** — navigates to the machine's transaction table

**Products Panel**
- Lists all registered inventory items available for sale
- Add an item to the terminal by selecting it and entering the desired quantity (only available if stock > 0)

**Transactions Panel**
- View all transactions made on the currently logged-in machine
- Refresh to pull the latest records

**Settings Panel**
- Displays all sales made by the currently logged-in user on the current machine

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| UI Framework | Java Swing |
| Database | MySQL |
| Build Tool | Apache Ant |
| Distribution | Executable JAR (`MPOS.jar`) |
| PDF Generation | iText (Encrypted PDF output) |

---

## 📁 Project Structure

```
MPOS/
├── src/                    # Java source files
├── build/                  # Compiled class files
├── dist/                   # Distribution JAR
├── lib/                    # External libraries
├── runnable/               # Ready-to-run JAR and assets
├── nbproject/              # NetBeans project config
├── build.xml               # Ant build script
├── manifest.mf             # JAR manifest
├── log.txt                 # Application log
├── mpos_manual_v1.pdf      # Full user manual (PDF)
└── mpos_manual_v1.html     # Full user manual (HTML)
```

---

## ⚙️ Prerequisites

- [Java JDK 8+](https://www.oracle.com/java/technologies/downloads/)
- [MySQL Server](https://dev.mysql.com/downloads/mysql/)
- (Optional) [Apache Ant](https://ant.apache.org/) — only needed to build from source

---

## 🚀 Installation & Setup

### Option 1 — Run the Prebuilt JAR (Recommended)

1. **Download and unzip** the repository:
   ```
   Download ZIP → Extract to a folder of your choice
   ```

2. **Start MySQL** and import the database schema (if a `.sql` file is provided), or configure your tables manually.

3. **Configure the database connection** in the source or config files:
   ```java
   String DB_URL  = "jdbc:mysql://localhost:3306/mpos_db";
   String DB_USER = "your_mysql_username";
   String DB_PASS = "your_mysql_password";
   ```

4. **Open a terminal**, navigate to the extracted folder, and run:
   ```bash
   cd "C:\Users\YourName\Documents\MPOS-main"
   java -jar "runnable/MPOS.jar"
   ```

### Option 2 — Build from Source

```bash
ant clean build
java -jar "dist/MPOS.jar"
```

---

## 📖 Usage

1. **Log in** using your Employee ID and password.
2. **Select a machine** from the list of registered, available machines.
3. **Admin/Staff** — use the Dashboard to manage items, suppliers, employees, machines, transactions, and reports.
4. **All roles** — use the POS Terminal to add products, process payments, and generate receipts.
5. **Generate reports** — export encrypted PDF invoices from the Reports panel (password: your username + machine name).

---

## 📄 Documentation

A full user manual is included in the repository:
- [`mpos_manual_v1.pdf`](./mpos_manual_v1.pdf) — PDF version
- [`mpos_manual_v1.html`](./mpos_manual_v1.html) — HTML version

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add your feature'`)
4. Push to your branch (`git push origin feature/your-feature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/).
