package teamrocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreCard {

    private final List<Integer> rolls = new ArrayList<>();

    public ScoreCard(String scoreCard) {
        parseRolls(scoreCard);
    }

    public List<Integer> getRolls() {
        return Collections.unmodifiableList(rolls);
    }

    public int score() {
        int score = 0;
        int rollIndex = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(rollIndex)) {
                score += 10 + strikeBonus(rollIndex);
                rollIndex += 1;
            } else if (isSpare(rollIndex)) {
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;
            } else {
                score += framePins(rollIndex);
                rollIndex += 2;
            }
        }

        return score;
    }

    private void parseRolls(String scoreCard) {
        for (int i = 0; i < scoreCard.length(); i++) {
            char ch = scoreCard.charAt(i);
            if (Character.isWhitespace(ch)) {
                continue;
            }
            if (ch == 'X') {
                rolls.add(10);
            } else if (ch == '-') {
                rolls.add(0);
            } else if (ch == '/') {
                int previous = rolls.get(rolls.size() - 1);
                rolls.add(10 - previous);
            } else if (Character.isDigit(ch)) {
                rolls.add(Character.getNumericValue(ch));
            } else {
                throw new IllegalArgumentException("Invalid roll character: " + ch);
            }
        }
    }

    private boolean isStrike(int rollIndex) {
        return rolls.get(rollIndex) == 10;
    }

    private boolean isSpare(int rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1) == 10;
    }

    private int strikeBonus(int rollIndex) {
        return rolls.get(rollIndex + 1) + rolls.get(rollIndex + 2);
    }

    private int spareBonus(int rollIndex) {
        return rolls.get(rollIndex + 2);
    }

    private int framePins(int rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1);
    }
}
