import { Component } from '@angular/core';
import { HorseService, Horse } from '../../services/horse.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-horse',
  templateUrl: './add-horse.component.html',
  styleUrls: ['./add-horse.component.css']
})
export class AddHorseComponent {
  horse: Horse = {
    name: '',
    birthDate: '',
    gender: 'Male',
    color: '',
    height: 0,
    weight: 0,
    birthmarks: '',
    category: { categoryId: 1 },  // ✅ Default category as an object
    handicapRating: 0
  };

  categories = [
    { id: 1, name: 'Thoroughbred' },
    { id: 2, name: 'Quarter Horse' },
    { id: 3, name: 'Arabian' },
    { id: 4, name: 'Standardbred' },
    { id: 5, name: 'Appaloosa' },
    { id: 6, name: 'Morgan' },
    { id: 7, name: 'Warmblood' },
    { id: 8, name: 'Draft Horse' },
    { id: 9, name: 'Shetland Pony' },
    { id: 10, name: 'Clydesdale' }
  ];

  successMessage: string = ''; // ✅ Holds success message
  errorMessage: string = '';   // ✅ Holds error message

  constructor(private horseService: HorseService) { }

  addHorse() {
    this.successMessage = '';
    this.errorMessage = '';

    this.horseService.createHorse(this.horse).subscribe({
      next: () => {
        this.successMessage = 'Horse added successfully!';
        this.resetForm(); // ✅ Clear form after success
      },
      error: () => {
        this.errorMessage = 'Error adding horse. Please try again.';
      }
    });
  }

  resetForm() {
    this.horse = {
      name: '',
      birthDate: '',
      gender: 'Male',
      color: '',
      height: 0,
      weight: 0,
      birthmarks: '',
      category: { categoryId: 1 },  // ✅ Default category as an object
      handicapRating: 0
    };
  }
}