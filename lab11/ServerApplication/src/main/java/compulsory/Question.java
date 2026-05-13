package compulsory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class Question {
    private String text;
    private String correctAnswer;
    private List<String> wrongAnswers;

    public static List<Question> loadFromFile(String filename) throws IOException {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();

                if (line.isBlank()) continue;

                String parts[] = line.split("\\|");

                if (parts.length < 5) continue;

                String text = parts[0];
                String correctAnswer = parts[1];
                List<String> wrongAnswers = List.of(parts[2].trim(), parts[3].trim(), parts[4].trim());

                questions.add(new Question(text, correctAnswer, wrongAnswers));
            }
        }

        return questions;
    }

    public List<String> shuffleOptions() {
        List<String> options = new ArrayList<>(wrongAnswers);
        options.add(correctAnswer);
        Collections.shuffle(options);
        return options;
    }

    public String getCorrectLetter(List<String> shuffledOptions) {
        int index = shuffledOptions.indexOf(correctAnswer);
        return String.valueOf((char) ('A' + index));
    }
}

