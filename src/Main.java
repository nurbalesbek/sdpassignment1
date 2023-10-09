import java.util.Scanner;

// Интерфейс стратегии для вычисления необходимого процента
interface ExamCalculationStrategy {
    double calculateRequiredPercentage(double inputPercentage);
}

// Реализация стратегии для стандартных вычислений
class StandardCalculationStrategy implements ExamCalculationStrategy {
    @Override
    public double calculateRequiredPercentage(double inputPercentage) {
        if (inputPercentage < 0 || inputPercentage > 100) {
            throw new IllegalArgumentException("Ошибка: Процент должен быть в пределах 0-100.");
        }

        double requiredFinalPercentage = 70.0;
        double requiredFinalPercentageScore = (requiredFinalPercentage - (inputPercentage * 0.6)) / 0.4;

        if (requiredFinalPercentageScore <= 100) {
            return requiredFinalPercentageScore;
        } else {
            throw new IllegalArgumentException("Ошибка: Невозможно достичь общего процента 70% и более.");
        }
    }
}

// Реализация стратегии для высоких оценок
class HighScoreCalculationStrategy implements ExamCalculationStrategy {
    @Override
    public double calculateRequiredPercentage(double inputPercentage) {
        if (inputPercentage < 0 || inputPercentage > 100) {
            throw new IllegalArgumentException("Ошибка: Процент должен быть в пределах 0-100.");
        }

        double requiredFinalPercentage = 90.0;
        double requiredFinalPercentageScore = (requiredFinalPercentage - (inputPercentage * 0.6)) / 0.4;

        if (requiredFinalPercentageScore <= 100) {
            return requiredFinalPercentageScore;
        } else {
            throw new IllegalArgumentException("Ошибка: Невозможно достичь общего процента 90% и более.");
        }
    }
}

// Класс, использующий стратегию
class ExamCalculatorContext {
    private ExamCalculationStrategy strategy;

    public void setStrategy(ExamCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateRequiredPercentage(double inputPercentage) {
        if (strategy == null) {
            throw new IllegalStateException("Ошибка: Стратегия не установлена.");
        }

        return strategy.calculateRequiredPercentage(inputPercentage);
    }
}

// Главный класс приложения
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите процент за Regterm: ");
        double regtermPercentage = scanner.nextDouble();

        ExamCalculatorContext calculatorContext = new ExamCalculatorContext();

        // Выбор стратегии в зависимости от условий
        if (regtermPercentage >= 90.0) {
            calculatorContext.setStrategy(new HighScoreCalculationStrategy());
        } else {
            calculatorContext.setStrategy(new StandardCalculationStrategy());
        }

        double requiredFinalPercentageScore = calculatorContext.calculateRequiredPercentage(regtermPercentage);

        System.out.println("Вам надо набрать не менее " + requiredFinalPercentageScore + "% на Final.");
    }
}
