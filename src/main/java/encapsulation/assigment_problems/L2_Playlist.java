package encapsulation.assigment_problems;

import java.util.Arrays;

/**
 * Assignment Problem 2: The Playlist
 * 
 * Demonstrates reference encapsulation and defensive copying:
 * - Stores song titles in a private array with a fixed maximum capacity.
 * - getSongs() returns a safe defensive COPY of all added songs.
 * - Mutating the returned array outside does NOT affect the playlist's real contents.
 * - Provides read-only count of recorded songs via getSongCount().
 */
public class L2_Playlist {

    /**
     * Playlist model class.
     */
    static class Playlist {
        // Private internal storage; never directly leaked to callers
        private final String[] songs;

        // Tracks the number of songs currently added
        private int count;

        /**
         * Constructs a Playlist with a fixed maximum capacity.
         *
         * @param capacity maximum number of songs the playlist can store
         */
        public Playlist(int capacity) {
            int size = Math.max(0, capacity);
            this.songs = new String[size];
            this.count = 0;
        }

        /**
         * Adds a song title to the playlist if capacity allows.
         *
         * @param song the title of the song
         * @return true if added, false if playlist is full or song is null
         */
        public boolean addSong(String song) {
            if (song != null && this.count < this.songs.length) {
                this.songs[this.count] = song;
                this.count++;
                return true;
            }
            return false;
        }

        /**
         * Returns a defensive copy of the songs added so far.
         * Mutating the returned array will not alter the internal playlist.
         *
         * @return a new String array containing all added songs in order
         */
        public String[] getSongs() {
            return Arrays.copyOf(this.songs, this.count);
        }

        /**
         * Returns the number of songs currently in the playlist.
         *
         * @return current song count
         */
        public int getSongCount() {
            return this.count;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: The Playlist ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");

        String[] copy = p.getSongs();
        System.out.println("Initial copy[0]: " + copy[0]);
        copy[0] = "Hacked";
        System.out.println("Modified copy[0] to: " + copy[0]);

        String originalFirstSong = p.getSongs()[0];
        System.out.println("p.getSongs()[0] is still: \"" + originalFirstSong + "\" (Defensive copy verified!)");
        System.out.println("p.getSongCount() -> " + p.getSongCount());

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Empty playlist returns empty array (length 0), not null
        Playlist empty = new Playlist(5);
        String[] emptySongs = empty.getSongs();
        System.out.println("Empty playlist: count = " + empty.getSongCount() + ", array length = " + emptySongs.length);

        // Edge Case 2: Full playlist capacity handling
        Playlist small = new Playlist(2);
        boolean a1 = small.addSong("Track 1");
        boolean a2 = small.addSong("Track 2");
        boolean a3 = small.addSong("Track 3"); // Exceeds capacity
        System.out.println("Track 1 added: " + a1 + ", Track 2 added: " + a2 + ", Track 3 (overflow) added: " + a3);
        System.out.println("Small playlist song count = " + small.getSongCount());
        System.out.println("Stored songs: " + Arrays.toString(small.getSongs()));

        // Edge Case 3: Modifying length or elements of copied array
        String[] smallCopy = small.getSongs();
        smallCopy[1] = "Corrupted";
        System.out.println("After mutating smallCopy, real small.getSongs()[1] = \"" + small.getSongs()[1] + "\"");
    }
}