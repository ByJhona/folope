import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardGFilmeComponent } from './card-g-filme.component';

describe('CardGFilmeComponent', () => {
  let component: CardGFilmeComponent;
  let fixture: ComponentFixture<CardGFilmeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardGFilmeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CardGFilmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
