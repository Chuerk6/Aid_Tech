import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LessonNewComponent } from './lesson-new.component';

describe('LessonNewComponent', () => {
  let component: LessonNewComponent;
  let fixture: ComponentFixture<LessonNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LessonNewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LessonNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
