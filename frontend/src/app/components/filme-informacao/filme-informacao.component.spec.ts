import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmeInformacaoComponent } from './filme-informacao.component';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('FilmeInformacaoComponent', () => {
  let component: FilmeInformacaoComponent;
  let fixture: ComponentFixture<FilmeInformacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
      imports: [FilmeInformacaoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FilmeInformacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
