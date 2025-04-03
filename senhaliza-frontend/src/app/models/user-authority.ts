import { Authority } from "./authority";
import { User } from "./user";

export interface UserAuthority{
    id: number;
    user: User;
    authority: Authority;   //FK
}