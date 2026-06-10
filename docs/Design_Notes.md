# Design Notes

## Why ArrayList instead of a plain array?

A plain array has a **fixed size** set when it is created. We do not know in advance how many students, courses, or enrollments a user will add. `ArrayList` grows automatically as items are added and offers convenient methods like `add()` and easy iteration, so it is the natural fit for dynamic, in-memory data. This is why all three repositories store their data in `ArrayList`s.

## Where static members are used and why

Static members belong to the class itself, not to any single object — there is exactly one shared copy. This is used in `IdGenerator`:

- `studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter` are `private static int` fields.
- `getNextStudentId()`, `getNextCourseId()`, `getNextEnrollmentId()` are `static` methods.

IDs must be **unique across the whole application**, regardless of how many objects exist. A shared static counter guarantees the next ID is always one higher than the last, no matter where it is requested. `AppConstants` and `MenuOptions` also use `static final` fields for fixed, application-wide values.

## Where inheritance is used and what it gained us

`Student extends Person`. The `Person` base class holds the fields common to any person — `id`, `firstName`, `lastName`, `email` — along with their getters/setters and a `getDisplayName()` method.

What we gained:

- **No duplication** — `Student` inherits the common fields instead of re-declaring them.
- **`super(...)`** in the `Student` constructor reuses `Person`'s constructor to initialise the shared fields.
- **Polymorphism** — `Student` overrides `getDisplayName()` to add a "Student: " prefix, showing how a subclass specialises inherited behaviour.

If a `Trainer extends Person` were added later, it would reuse the same base for free.

## Clean code choices

- Each layer has one responsibility: **entity** (data), **repository** (storage), **service** (business rules), **Main** (UI only).
- Methods are short and named for what they do (`addStudent`, `findCourseById`, `toggleCourseStatus`).
- Input validation lives in one place (`InputValidator`), and errors surface as readable messages instead of crashing the program.