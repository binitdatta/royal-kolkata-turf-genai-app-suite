import { Component, OnInit } from '@angular/core';
import { HorseService, Horse } from '../../services/horse.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-horse-grid',
  templateUrl: './horse-grid.component.html',
  styleUrls: ['./horse-grid.component.css']
})
export class HorseGridComponent implements OnInit {
  horses: Horse[] = [];
  isLoading: boolean = true;
  isError: boolean = false;

  constructor(private horseService: HorseService, private router: Router) { }

  ngOnInit(): void {
    this.loadHorses();
  }

  loadHorses() {
    this.isLoading = true;
    this.isError = false;

    this.horseService.getAllHorses().subscribe({
      next: (data) => {
        this.horses = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading horses:', error);
        this.isLoading = false;
        this.isError = true;
      }
    });
  }

  editHorse(horseId: number) {
    this.router.navigate(['/app-edit-horse', horseId]); // ✅ Navigate to Edit Horse component
  }
}
