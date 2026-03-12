# PeerLink File Sharing System

![Java](https://img.shields.io/badge/Language-Java-blue)
![Networking](https://img.shields.io/badge/Concept-Socket%20Programming-green)
![Architecture](https://img.shields.io/badge/System-Peer%20to%20Peer-orange)
![Type](https://img.shields.io/badge/Project-Distributed%20Systems-red)

PeerLink is a **peer-to-peer file sharing system built using Java sockets**.
The project demonstrates how distributed systems can enable **direct file transfer between peers while using a tracker server for peer discovery**.

It simulates the architecture used in real-world systems such as **BitTorrent-like file sharing networks**.

---

# 🚀 Features

* Peer-to-peer file sharing using **Java sockets**
* Tracker server for **peer discovery**
* Direct file transfer between peers
* Multithreaded peer server for handling multiple requests
* Simple distributed system architecture
* Modular project structure

---

# 🏗 System Architecture

```text
Peer Client
     ↓
Tracker Server
     ↓
Peer Discovery
     ↓
Peer Server
     ↓
File Transfer
```

Workflow:

1. Peer registers with the **tracker server**.
2. Client requests **available peers** from tracker.
3. Client connects to a **peer server**.
4. File is transferred directly between peers.

---

# 📂 Project Structure

```text
peerlink-file-sharing
│
├── assets
│   ├── peer-client-download.png
│   ├── peer-server-running.png
│   ├── peer-transfer.png
│   └── tracker-running.png
│
├── shared
│   └── example.txt
│
├── src
│   ├── peer
│   │   ├── PeerClient.java
│   │   └── PeerServer.java
│   │
│   ├── tracker
│   │   └── TrackerServer.java
│   │
│   └── utils
│       └── FileUtils.java
│
├── README.md
└── .gitignore
```

---

# 🌐 Components

## Tracker Server

Responsible for **peer discovery**.

Functions:

* Registers peers
* Stores peer addresses
* Returns available peers to clients

---

## Peer Server

Acts as a **file provider**.

Functions:

* Hosts shared files
* Listens for file requests
* Sends requested files to peers

---

## Peer Client

Responsible for **downloading files from peers**.

Functions:

* Connects to peer server
* Requests file
* Saves downloaded file locally

---

# 🖥 Screenshots

## Tracker Server Running

![Tracker](assets/tracker-running.png)

---

## Peer Server Running

![Peer Server](assets/peer-server-running.png)

---

## Peer Client Downloading File

![Peer Client](assets/peer-client-download.png)

---

## File Transfer Result

![Transfer](assets/peer-transfer.png)

---

# ⚙️ Running the Project

## Compile

```bash
javac -d out src/peer/*.java src/tracker/*.java src/utils/*.java
```

---

## Start Tracker Server

```bash
java -cp out tracker.TrackerServer
```

---

## Start Peer Server

```bash
java -cp out peer.PeerServer
```

---

## Run Peer Client

```bash
java -cp out peer.PeerClient
```

---

# 📄 Example File

Example shared file:

```
shared/example.txt
```

Contents:

```
Hello from PeerLink file sharing system.
This file is transferred using Java sockets.
```

---

# 🧠 Concepts Demonstrated

This project demonstrates:

* Java socket programming
* Peer-to-peer networking
* Distributed system basics
* File transfer protocols
* Multithreaded server architecture

---

# 🔮 Future Improvements

Possible upgrades:

* Chunk-based file transfer
* Parallel downloads from multiple peers
* Peer authentication
* File integrity verification using hashing
* GUI interface for file sharing

---

# 👨‍💻 Author

**Sahil**

B.Tech Information Technology
Galgotias College of Engineering and Technology

GitHub
https://github.com/sahilsingh78
