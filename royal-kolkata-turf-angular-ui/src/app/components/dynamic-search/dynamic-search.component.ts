import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-dynamic-search',
  templateUrl: './dynamic-search.component.html',
  styleUrls: ['./dynamic-search.component.css']
})
export class DynamicSearchComponent {
  apiUrl = 'http://localhost:8079/api/analytics/search'; // Backend API

  // Ensure results is initialized as an empty array
  results: any[] = [];
  isLoading = false;
  isError = false;

  searchCriteria = {
    horseName: '',
    color: '',
    birthDate: '',
    category: '',
    gender: ''
  };

  horseCategories = [
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

  constructor(private http: HttpClient) { }

  searchHorses() {
    this.isLoading = true;
    this.isError = false;

    let params = new HttpParams();

    if (this.searchCriteria.horseName) {
      params = params.set('horseName', this.searchCriteria.horseName.trim());
    }
    if (this.searchCriteria.color && this.searchCriteria.color.trim() !== '') {
      params = params.set('color', this.searchCriteria.color.trim());
    }

    if (this.searchCriteria.birthDate) {
      params = params.set('birthDate', this.searchCriteria.birthDate);
    }
    if (this.searchCriteria.category) {
      params = params.set('categoryId', this.searchCriteria.category.toString()); // Send category ID
    }
    if (this.searchCriteria.gender && this.searchCriteria.gender.trim() !== '') {
      params = params.set('gender', this.searchCriteria.gender.trim());
    }


    // Debugging: Print the final API URL
    console.log('Final API URL:', `${this.apiUrl}?${params.toString()}`);

    this.http.get<{ results: any[] }>(this.apiUrl, { params }).subscribe(
      response => {
        console.log('API Response:', response);
        console.log('API Response results:', response.results);

        if (Array.isArray(response)) {
          this.results = response; // Directly assign if API returns an array
        } else if (response && response.results) {
          this.results = response.results; // Fallback if response is an object
        } else {
          this.results = []; // Default to empty array
        }

        console.log('Updated results array:', this.results);
        this.isLoading = false;
      },
      error => {
        console.error('Error fetching search results:', error);
        this.results = [];  // Reset results to an empty array
        this.isLoading = false;
        this.isError = true;
      }
    );
  }

  validateDate() {
    if (this.searchCriteria.birthDate) {
      const datePattern = /^\d{4}-\d{2}-\d{2}$/; // YYYY-MM-DD format
      if (!datePattern.test(this.searchCriteria.birthDate)) {
        console.warn("Invalid date format. Expected format: YYYY-MM-DD");
        this.searchCriteria.birthDate = ''; // Reset if invalid
      }
    }
  }

  resetSearchPage() {
    this.searchCriteria = {
      horseName: '',
      color: '',
      birthDate: '',
      category: '',
      gender: ''
    };

    this.results = [];  // Clear the results
    this.isError = false;
    this.isLoading = false;

    console.log("Search page reset.");
  }


}