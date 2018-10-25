import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogMysqlComponent } from './log-mysql.component';

describe('LogMysqlComponent', () => {
  let component: LogMysqlComponent;
  let fixture: ComponentFixture<LogMysqlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogMysqlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogMysqlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
