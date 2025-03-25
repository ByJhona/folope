import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmeComponent } from './filme.component';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('FilmeComponent', () => {
  let component: FilmeComponent;
  let fixture: ComponentFixture<FilmeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
      imports: [FilmeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FilmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
