import { Exercise } from "./exercise";
import { Student } from "./student";

export interface StudentExercise{
    id: number;
    answer_date: string;
    correct: boolean;
    exercise: Exercise; // FK
    student: Student;   //FK
}