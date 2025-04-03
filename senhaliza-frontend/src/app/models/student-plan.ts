import { Plan } from "./plan";
import { Student } from "./student";

export interface StudentPlan{
    id: number;
    startDate: string;
    endDate: string;
    student: Student;   // FK
    plan: Plan;         // FK
}