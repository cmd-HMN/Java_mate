# 👑 Java_mate 👑

A chess engine written in Java, designed to build the engine and GUI for a chess game.

## Features 🔑

- **Game Logic**: The engine will be able to handle basic game logic such as moving pieces, and checking for checkmate.

- **GUI**: The engine will be able to build a GUI for the chess game using Java Swing.

## Installation 🪛

To run this, you will need to have Java installed on your computer. You can download the

1. Download the below files:

    - [Java](https://www.java.com/download/ie_manual.jsp)
    - [JDK](https://www.oracle.com/pk/java/technologies/downloads/) (prefer) or [OpenJDK](https://adoptopenjdk.net/downloads.html)

2. Get the repo:

    Then clone the repo

    ```bash
       gh repo clone cmd-HMN/Java_mate
    ```

    or

    download the zip file from [here](https://github.com/cmd-HMN/Java_mate/archive/refs/heads/main.zip) then extract the files

3. Run the file

    We need to build and then run the game. (Use PowerShell or Terminal or any code editor.)
   
    Run below commands
    
    ```bash
        cd ...\JavaMate  //(go the dir where u have extract the files, root folder)
         --build--
        javac src/engine/*.java src/gui/*.java
        -- after that run the below command--
        java src.gui.ChessBoard
    ```
    Hurray!🙌 it worked.

## Motivation

*"I started this little chess engine project just for fun, really – something to keep me busy and, yeah, maybe even level up my coding skills while I’m at it! 😎 It’s been a wild mix of battling code bugs and figuring out cool chess moves, but I’m here for the challenge. Every line of code is like a puzzle piece fitting into place, making me better at this whole software thing one move at a time. Just enjoying the process and seeing where it goes! 🚀"*

##🛠️ Known Issues & Limitations:

#### Gameplay Restricted to White: 
    Only the white pieces are functional right now, giving white players an unfair advantage 😅
#### Existing Bugs & Errors 🐞:
    Certain moves might not work as intended (It's like our rook is trying to be a knight sometimes 😂)
    Movement validation is limited and may allow illegal moves or miss valid ones.

## Contributing 💖

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

## License 📖🔓

This project is licensed under the [GNU Affero General Public License (AGPL) v3.0](https://opensource.org/licenses/AGPL-3.0).
