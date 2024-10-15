import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmeInformacaoComponent } from './filme-informacao.component';

describe('FilmeInformacaoComponent', () => {
  let component: FilmeInformacaoComponent;
  let fixture: ComponentFixture<FilmeInformacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
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
