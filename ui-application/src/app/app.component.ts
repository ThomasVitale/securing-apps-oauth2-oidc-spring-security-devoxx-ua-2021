import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpXsrfTokenExtractor } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Polar Bookshop';

  isAuthenticated = false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(
    private authService: AuthService,
    private breakpointObserver: BreakpointObserver,
    private httpXsrfTokenExtractor: HttpXsrfTokenExtractor,
  ) {}

  ngOnInit(): void {
    this.authService.authenticate().subscribe(result => {
      if (result) {
        this.isAuthenticated = true;
      }
    });
  }

  logInClicked(): void {
    this.authService.login();
  }

  csrfToken(): string | null {
    return this.httpXsrfTokenExtractor.getToken();
  }

}
