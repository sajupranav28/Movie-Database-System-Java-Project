import java.util.Scanner;

class Movie {
    String title;
    int releaseYear;
    String director;
    double rating;

    public Movie(String title, int releaseYear, String director, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.director = director;
        this.rating = rating;
    }
}

class MovieDatabase {
    Node root;

    static class Node {
        Movie movie;
        Node left, right;

        public Node(Movie movie) {
            this.movie = movie;
            this.left = this.right = null;
        }
    }

    // Insert a movie into the BST
    public void insertMovie(Movie movie) {
        root = insert(root, movie);
    }

    private Node insert(Node node, Movie movie) {
        if (node == null) {
            return new Node(movie);
        }
        if (movie.title.compareTo(node.movie.title) < 0) {
            node.left = insert(node.left, movie);
        } else if (movie.title.compareTo(node.movie.title) > 0) {
            node.right = insert(node.right, movie);
        }
        return node;
    }

    // Search for a movie by title
    public Movie searchMovie(String title) {
        return search(root, title);
    }

    private Movie search(Node node, String title) {
        if (node == null || node.movie.title.equals(title)) {
            return (node != null) ? node.movie : null;
        }
        if (title.compareTo(node.movie.title) < 0) {
            return search(node.left, title);
        } else {
            return search(node.right, title);
        }
    }

    // Delete a movie by title
    public void deleteMovie(String title) {
        root = delete(root, title);
    }

    private Node delete(Node node, String title) {
        if (node == null) {
            return null;
        }
        if (title.compareTo(node.movie.title) < 0) {
            node.left = delete(node.left, title);
        } else if (title.compareTo(node.movie.title) > 0) {
            node.right = delete(node.right, title);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.movie = findMin(node.right).movie;
            node.right = delete(node.right, node.movie.title);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieDatabase movieDB = new MovieDatabase();

        while (true) {
            System.out.println("1. Add Movie");
            System.out.println("2. Search Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter release year: ");
                    int releaseYear = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    movieDB.insertMovie(new Movie(title, releaseYear, director, rating));
                    break;
                case 2:
                    System.out.print("Enter movie title to search: ");
                    String searchTitle = scanner.nextLine();
                    Movie searchedMovie = movieDB.searchMovie(searchTitle);
                    if (searchedMovie != null) {
                        System.out.println("Found movie: " + searchedMovie.title);
                        System.out.println("Director: " + searchedMovie.director);
                        System.out.println("Rating: " + searchedMovie.rating);
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter movie title to delete: ");
                    String deleteTitle = scanner.nextLine();
                    movieDB.deleteMovie(deleteTitle);
                    break;
                case 4:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
