import { TicketData } from "./ticket-data";

export class MovieData {
    movieId!: number;
    movieName!: string;
    theaterName!: string;
    totalTickets!: number;
    ticketStatus!: string;
    bookedSeats!: string[];
    tickets!: TicketData[];
}
