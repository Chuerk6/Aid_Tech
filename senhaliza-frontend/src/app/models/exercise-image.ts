import { Exercise } from "./exercise";
import { Image } from "./image";

export interface ExerciseImage{
    id: number;
    correct_option: boolean;
    correct_answer: string;
    exercise: Exercise; // FK
    image: Image;       //FK
}