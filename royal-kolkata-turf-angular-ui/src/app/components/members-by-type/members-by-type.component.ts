import { Component } from '@angular/core';
import { MembersByType } from 'src/app/models/members-by-type';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';

@Component({
  selector: 'app-members-by-type',
  templateUrl: './members-by-type.component.html',
  styleUrls: ['./members-by-type.component.css']
})
export class MembersByTypeComponent {
  membersByType?: MembersByType[];
  memberByType?: MembersByType = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getMembersByType()
      .subscribe({
        next: (data) => {
          this.membersByType = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}



