# MoviesHub

Welcome to MoviesHub! This application allows users to browse, search for, and view details about movies. Administrators have additional capabilities to manage the movie library.

## Features

-   **User Authentication:** Secure login for both regular users and administrators.
-   **Movie Browsing:** Users can view a list of available movies.
-   **Movie Search:** Users and admins can search for movies by title.
-   **Movie Details:** View detailed information about individual movies.
-   **Admin Movie Management:**
    -   Search for movies from an external API and add them to the database.
    -   View and remove movies from the database.
    -   (Potentially) Batch add or remove movies.
-   (Future) Movie Rating: Users can rate movies.
-   (Future) Pagination: Improved display of large movie lists.

## Technologies Used

-   Angular (for the frontend)
-  Spring Boot 
-  Mysql 
-   OMDb API (for fetching movie details)

## Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/mohamedgendy-learn/MovieHub-backend.git
    cd movieshub
    ```

3.  **Install backend dependencies:**
    ```bash
    cd backend  # Or the directory containing your backend project
    npm install
    ```

4.  **Configure the backend:**
    -   Set up your database connection.
    -   Obtain an API key for the OMDb API and configure it in your backend.

5.  **Run the backend:**
    ```bash
    cd backend
    npm start  # Or your backend's run command
    ```


## Usage

-   Navigate to the application URL in your web browser.
-   Log in with your credentials.
-   Regular users can browse and search for movies.
-   Administrators can access additional movie management features.


