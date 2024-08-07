import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BarraPesquisarComponent } from './barra-pesquisar.component';

describe('BarraPesquisarComponent', () => {
  let component: BarraPesquisarComponent;
  let fixture: ComponentFixture<BarraPesquisarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BarraPesquisarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BarraPesquisarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
