import { TestBed } from '@angular/core/testing';

import { FilmeService } from './filme.service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('FilmeService', () => {
  let service: FilmeService;

  beforeEach(() => {
    TestBed.configureTestingModule({providers: [provideHttpClient(), provideHttpClientTesting()]});
    service = TestBed.inject(FilmeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
