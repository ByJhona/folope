import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadoPesquisaComponent } from './resultado-pesquisa.component';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

describe('ResultadoPesquisaComponent', () => {
  let component: ResultadoPesquisaComponent;
  let fixture: ComponentFixture<ResultadoPesquisaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
      imports: [ResultadoPesquisaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ResultadoPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
