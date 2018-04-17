import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
       List<Song> expectedList = Arrays.asList( new Song[] {
            new Song("Avett Brothers", "Talk on Indolence", 2006),
            new Song("City and Colour", "Sleeping Sickness", 2007),
            new Song("Decemberists", "The Mariner's Revenge Song", 2005),
            new Song("Foo Fighters", "Baker Street", 1997),
            new Song("Gerry Rafferty", "Baker Street", 1978),
            new Song("Gerry Rafferty", "Baker Street", 1998),
            new Song("Queen", "Bohemian Rhapsody", 1975),
            new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
        }
       );

       List<Song> list = Arrays.asList( new Song[] {
          new Song("Foo Fighters", "Baker Street", 1997),
          new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
          new Song("Gerry Rafferty", "Baker Street", 1978),
          new Song("Decemberists", "The Mariner's Revenge Song", 2005),
          new Song("Avett Brothers", "Talk on Indolence", 2006),
          new Song("City and Colour", "Sleeping Sickness", 2007),
          new Song("Queen", "Bohemian Rhapsody", 1975),
          new Song("Gerry Rafferty", "Baker Street", 1998)
      });
       list.sort(new ArtistComparator());
       assertEquals(expectedList, list );
   }

   @Test
   public void testLambdaTitleComparator()
   {
        List<Song> expectedList = Arrays.asList( new Song[]{
            new Song("Foo Fighters", "Baker Street", 1997),
            new Song("Gerry Rafferty", "Baker Street", 1978),
            new Song("Queen", "Bohemian Rhapsody", 1975),
            new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
            new Song("City and Colour", "Sleeping Sickness", 2007),
            new Song("Avett Brothers", "Talk on Indolence", 2006),
            new Song("Decemberists", "The Mariner's Revenge Song", 2005)
        });
        List<Song> list = Arrays.asList( new Song[] {
            new Song("Foo Fighters", "Baker Street", 1997),
            new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
            new Song("Gerry Rafferty", "Baker Street", 1978),
            new Song("Decemberists", "The Mariner's Revenge Song", 2005),
            new Song("Avett Brothers", "Talk on Indolence", 2006),
            new Song("City and Colour", "Sleeping Sickness", 2007),
            new Song("Queen", "Bohemian Rhapsody", 1975),
        });
      Comparator<Song> comp = (Song s1, Song s2) -> (s1.getTitle().compareTo(s2.getTitle()));
      Collections.sort(list, comp);
      assertEquals(expectedList, list);
   }

   @Test
   public void testYearExtractorComparator()
   {
        List<Song> list = Arrays.asList( new Song[] {
           new Song("Foo Fighters", "Baker Street", 1997),
           new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
           new Song("Gerry Rafferty", "Baker Street", 1978),
           new Song("Decemberists", "The Mariner's Revenge Song", 2005),
           new Song("Avett Brothers", "Talk on Indolence", 2006),
           new Song("City and Colour", "Sleeping Sickness", 2007),
           new Song("Queen", "Bohemian Rhapsody", 1975),
           new Song("Gerry Rafferty", "Baker Street", 1998)
       });

       List<Song> expectedList = Arrays.asList( new Song[] {
           new Song("Queen", "Bohemian Rhapsody", 1975),
           new Song("Gerry Rafferty", "Baker Street", 1978),
          new Song("Foo Fighters", "Baker Street", 1997),
          new Song("Gerry Rafferty", "Baker Street", 1998),
          new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
          new Song("Decemberists", "The Mariner's Revenge Song", 2005),
          new Song("Avett Brothers", "Talk on Indolence", 2006),
          new Song("City and Colour", "Sleeping Sickness", 2007),
      });
       Comparator<Song> comp = Comparator.comparing(Song :: getYear);
       Collections.sort(list, comp);
       assertEquals(expectedList, list);
   }

   @Test
   public void testComposedComparator()
   {
       List<Song> list = Arrays.asList( new Song[] {
          new Song("Foo Fighters", "Baker Street", 1997),
          new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
          new Song("Gerry Rafferty", "Baker Street", 1978),
          new Song("Decemberists", "The Mariner's Revenge Song", 2005),
          new Song("Avett Brothers", "Talk on Indolence", 2006),
          new Song("City and Colour", "Sleeping Sickness", 2007),
          new Song("Queen", "Bohemian Rhapsody", 1975),
          new Song("Gerry Rafferty", "Baker Street", 1998)
      });

      List<Song> expected = Arrays.asList(
            new Song("Avett Brothers", "Talk on Indolence", 2006),
            new Song("City and Colour", "Sleeping Sickness", 2007),
            new Song("Decemberists", "The Mariner's Revenge Song", 2005),
            new Song("Foo Fighters", "Baker Street", 1997),
            new Song("Gerry Rafferty", "Baker Street", 1978),
            new Song("Gerry Rafferty", "Baker Street", 1998),
            new Song("Queen", "Bohemian Rhapsody", 1975),
            new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
     );

     Collections.sort(list, new ComposedComparator(
        Comparator.comparing(Song :: getArtist),
        Comparator.comparing(Song :: getYear)
     ));
     assertEquals(list, expected);
   }

   @Test
   public void testThenComparing()
   {
       List<Song> list = Arrays.asList( new Song[] {
          new Song("Foo Fighters", "Baker Street", 1997),
          new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
          new Song("Gerry Rafferty", "Baker Street", 1978),
          new Song("Decemberists", "The Mariner's Revenge Song", 2005),
          new Song("Avett Brothers", "Talk on Indolence", 2006),
          new Song("City and Colour", "Sleeping Sickness", 2007),
          new Song("Queen", "Bohemian Rhapsody", 1975),
          new Song("Gerry Rafferty", "Baker Street", 1998)
      });

      // sort by artist and then by year
      List<Song> expected = Arrays.asList(
            new Song("Avett Brothers", "Talk on Indolence", 2006),
            new Song("City and Colour", "Sleeping Sickness", 2007),
            new Song("Decemberists", "The Mariner's Revenge Song", 2005),
            new Song("Foo Fighters", "Baker Street", 1997),
            new Song("Gerry Rafferty", "Baker Street", 1978),
            new Song("Gerry Rafferty", "Baker Street", 1998),
            new Song("Queen", "Bohemian Rhapsody", 1975),
            new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
     );

    Comparator<Song> comp = Comparator.comparing(Song :: getArtist).thenComparing(Song :: getYear);
    Collections.sort(list,comp);

    assertEquals(expected, list);


   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      songList.sort(
        new ArtistComparator().thenComparing(Song :: getTitle).thenComparing(Song :: getYear)
      );
      assertEquals(songList, expectedList);
   }
}
