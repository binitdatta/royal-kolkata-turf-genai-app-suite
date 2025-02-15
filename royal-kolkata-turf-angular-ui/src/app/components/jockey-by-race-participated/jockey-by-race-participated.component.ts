import { Component } from '@angular/core';
import { JockeyByRaceParticipated } from 'src/app/models/jockey-by-rape-participated';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';

@Component({
  selector: 'app-jockey-by-race-participated',
  templateUrl: './jockey-by-race-participated.component.html',
  styleUrls: ['./jockey-by-race-participated.component.css']
})
export class JockeyByRaceParticipatedComponent {
  jockeysByRaceParticipated?: JockeyByRaceParticipated[];
  jockeyByRaceParticipated?: JockeyByRaceParticipated = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getJockeysByRaceParticipated()
      .subscribe({
        next: (data) => {
          this.jockeysByRaceParticipated = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}




