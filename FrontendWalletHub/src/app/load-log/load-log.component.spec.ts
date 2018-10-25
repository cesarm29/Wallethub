import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadLogComponent } from './load-log.component';

describe('LoadLogComponent', () => {
  let component: LoadLogComponent;
  let fixture: ComponentFixture<LoadLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoadLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
