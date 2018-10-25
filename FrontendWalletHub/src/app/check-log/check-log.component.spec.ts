import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckLogComponent } from './check-log.component';

describe('CheckLogComponent', () => {
  let component: CheckLogComponent;
  let fixture: ComponentFixture<CheckLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
