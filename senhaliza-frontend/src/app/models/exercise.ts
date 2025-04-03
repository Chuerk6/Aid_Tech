import { Lesson } from "./lesson";

export interface Exercise{
    id: number;
    level: string;
    type_question: string;
    question: string;
    comment: string;
    lesson: Lesson;     //FK
}