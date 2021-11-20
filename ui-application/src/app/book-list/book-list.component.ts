import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { Book } from '../types';
import { BookService } from '../book.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: Book[] = [];

  colNumber = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => matches ? 2 : 3));

  constructor(
    public dialog: MatDialog,
    private bookService: BookService,
    private breakpointObserver: BreakpointObserver,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bookService.getBooks().subscribe(books => this.books = books);
  }

}
