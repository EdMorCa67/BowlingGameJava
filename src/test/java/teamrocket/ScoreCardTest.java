package teamrocket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ScoreCardTest {

    @Test
    public void perfectGameReturns300() {
        ScoreCard card = new ScoreCard("XXXXXXXXXXXX");
        assertEquals(300, card.score());
    }

    @Test
    public void allGutterGameReturns0() {
        ScoreCard card = new ScoreCard("--------------------");
        assertEquals(0, card.score());
    }

    @Test
    public void allOnesReturn20() {
        ScoreCard card = new ScoreCard("11111111111111111111");
        assertEquals(20, card.score());
    }

    @Test
    public void spareFollowedByThreeScores16() {
        ScoreCard card = new ScoreCard("5/3-----------------");
        assertEquals(16, card.score());
    }

    @Test
    public void nineMissForEveryFrameReturns90() {
        ScoreCard card = new ScoreCard("9-9-9-9-9-9-9-9-9-9-");
        assertEquals(90, card.score());
    }

    @Test
    public void scoreCardAcceptsWhitespace() {
        ScoreCard card = new ScoreCard("X 7/ 9- X- 88/ -6 XXX 81");
        assertEquals(167, card.score());
    }

    @Test
    public void invalidCharactersThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new ScoreCard("X7/A9-"));
    }
}
