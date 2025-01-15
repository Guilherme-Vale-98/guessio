import { GameInterface } from "./GameInterface";

export interface MatchInterface {
    id: string;
    answer: GameInterface;
    date: string;
    attempts: GameInterface[];
    status: string;
  }