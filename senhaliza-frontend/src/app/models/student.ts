import { Level } from "./level";
import { User } from "./user";

export interface Student{
    id: number;
    name: string;
    email: string;
    phone: string;
    avatar: string;
    level: Level;
    user: User;     // FK
}