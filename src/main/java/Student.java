
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
    public class Student{
        private String name;
        private int kor;
        private int eng;
        private int math;
    }