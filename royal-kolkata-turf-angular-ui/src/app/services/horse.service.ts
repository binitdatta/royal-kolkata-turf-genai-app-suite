import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Horse {
    horseId?: number;
    name: string;
    birthDate: string;
    gender: 'Male' | 'Female';
    color: string;
    height: number;
    weight: number;
    birthmarks: string;
    category: { categoryId: number, categoryName?: string };  // ✅ Include categoryName
    handicapRating: number;
}


@Injectable({
    providedIn: 'root'
})
export class HorseService {
    private apiUrl = 'http://localhost:8079/api/horses';

    constructor(private http: HttpClient) { }

    getAllHorses(): Observable<Horse[]> {
        return this.http.get<Horse[]>(this.apiUrl);
    }

    getHorseById(id: number): Observable<Horse> {
        return this.http.get<Horse>(`${this.apiUrl}/${id}`);
    }

    createHorse(horse: Horse): Observable<Horse> {
        return this.http.post<Horse>(this.apiUrl, {
            ...horse,
            category: { categoryId: horse.category.categoryId } // ✅ Ensure the correct format
        });
    }

    updateHorse(id: number, horse: Horse): Observable<Horse> {
        return this.http.put<Horse>(`${this.apiUrl}/${id}`, {
            ...horse,
            category: { categoryId: horse.category.categoryId } // ✅ Ensure the correct format
        });
    }
}
