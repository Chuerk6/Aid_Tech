import { Lesson } from "./lesson";
import { Student } from "./student";

export interface LessonStudent{
    id: number;
    status: string;
    lesson: Lesson;     //FK
    student: Student;   //FK
}