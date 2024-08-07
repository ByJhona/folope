import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BntTrailerComponent } from './bnt-trailer.component';

describe('BntTrailerComponent', () => {
  let component: BntTrailerComponent;
  let fixture: ComponentFixture<BntTrailerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BntTrailerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BntTrailerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
