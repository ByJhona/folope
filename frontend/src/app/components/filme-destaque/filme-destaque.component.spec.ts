import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmeDestaqueComponent } from './filme-destaque.component';

describe('FilmeDestaqueComponent', () => {
  let component: FilmeDestaqueComponent;
  let fixture: ComponentFixture<FilmeDestaqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilmeDestaqueComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FilmeDestaqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
