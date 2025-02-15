import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HorseService, Horse } from '../../services/horse.service';


@Component({
  selector: 'app-edit-horse',
  templateUrl: './edit-horse.component.html',
  styleUrls: ['./edit-horse.component.css']
})
export class EditHorseComponent implements OnInit {

  successMessage: string = ''; // ✅ Success message
  errorMessage: string = '';   // ❌ Error message

  horse: Horse = {  // ✅ Initialize with default values
    horseId: 0,
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

  constructor(
    private route: ActivatedRoute,
    private horseService: HorseService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.horseService.getHorseById(id).subscribe({
      next: (data) => {
        if (data) {
          this.horse = {
            ...data,
            birthDate: this.formatDate(data.birthDate),  // ✅ Format date before binding
            category: {
              categoryId: data.category?.categoryId || 1,
              categoryName: data.category?.categoryName || 'Unknown'
            }
          };
        }
      },
      error: () => {
        this.errorMessage = 'Failed to load horse details.';
      }
    });
  }

  // ✅ Function to Convert Date to "YYYY-MM-DD" Format
  private formatDate(dateString: string): string {
    if (!dateString) return '';
    return dateString.split('T')[0];  // ✅ Extract YYYY-MM-DD part
  }

  updateHorse() {
    this.successMessage = '';
    this.errorMessage = '';

    this.horseService.updateHorse(this.horse.horseId!, this.horse).subscribe({
      next: () => {
        this.successMessage = 'Horse updated successfully!';
      },
      error: () => {
        this.errorMessage = 'Error updating horse. Please try again.';
      }
    });
  }

  // ✅ Navigate back to the horse grid
  goBackToGrid() {
    this.router.navigate(['/app-horse-grid']);
  }
  cancelEdit() {
    this.router.navigate(['/app-horse-grid']); // Redirects to the horse list (grid)
  }
}
