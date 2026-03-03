

# 📘 Business Requirements Document

## Project Title

**Team-Based Project & Task Review Management System**

---

# 1️⃣ Purpose

To build a backend REST system that allows:

* Users to form Teams
* Teams to manage Projects
* Managers to create Tasks
* Assigned reviewers to review Tasks
* Enforce structured approval workflow

The system must enforce business rules at service layer without JWT/auth complexity.

---

# 2️⃣ Scope

This system will:

* Manage Users
* Manage Teams
* Manage Projects under Teams
* Manage Tasks under Projects
* Assign Reviewers to Tasks
* Enforce Review-based approval workflow
* Provide Dashboard statistics

Not in scope:

* Authentication (JWT)
* UI implementation
* Notifications
* Real-time updates

---

# 3️⃣ Actors

### 1. ADMIN

* Can create users
* Can create teams
* Can be part of any team
* Can act as reviewer

### 2. MANAGER

* Can create projects (for their team)
* Can create tasks (for their team projects)
* Can assign reviewers
* Can review tasks if assigned

### 3. MEMBER

* Can be assigned tasks
* Can update their assigned tasks
* Can review only if assigned

---

# 4️⃣ Core Entities

## User

* id
* name
* email
* role (ADMIN / MANAGER / MEMBER)
* created_at

Role is global.

---

## Team

* id
* name
* created_by (User)
* created_at

---

## TeamMember

* id
* team_id
* user_id
* joined_at

(User can belong to multiple teams)

---

## Project

* id
* name
* description
* team_id
* created_at

One team → many projects
One project → one team

---

## Task

* id
* title
* description
* status (TODO, IN_PROGRESS, DONE, REOPENED)
* priority (LOW, MEDIUM, HIGH)
* project_id
* assigned_to
* created_by
* due_date
* created_at

---

## TaskReviewer

* id
* task_id
* reviewer_id
* review_status (PENDING, APPROVED, REJECTED)
* rating (1–5)
* feedback
* reviewed_at

One task → multiple reviewers

---

# 5️⃣ Business Rules

## BR-1: User Management

* Only ADMIN can create users.
* User email must be unique.

---

## BR-2: Team Management

* Only ADMIN can create teams.
* Only existing users can be added to team.
* A user can be part of multiple teams.

---

## BR-3: Project Management

* Only MANAGER (or ADMIN) of that team can create project.
* Project must belong to exactly one team.
* Cannot delete team if active projects exist.

---

## BR-4: Task Creation

Task can be created only if:

* Creator role is MANAGER or ADMIN.
* Creator belongs to project’s team.
* Assigned user belongs to same team.

---

## BR-5: Reviewer Assignment

* Reviewers must belong to same team.
* Reviewer cannot be the task creator.
* Reviewer cannot be the assigned user (no self-review).
* At least one reviewer must be assigned.

---

## BR-6: Task Status Flow

Valid transitions:

```text
TODO → IN_PROGRESS → DONE
DONE → REOPENED (if rejected)
REOPENED → IN_PROGRESS
```

Rules:

* Only assigned user can change status.
* Task cannot move to DONE unless all reviewers have reviewed.
* If any reviewer REJECTS → status becomes REOPENED.
* If all APPROVE → status remains DONE.

---

## BR-7: Review Rules

* Only assigned reviewers can review.
* Reviewer can review only once.
* Review allowed only when task status = DONE.
* Rating must be between 1–5.

---

## BR-8: Deletion Rules

* Cannot delete team if projects exist.
* Cannot delete project if tasks exist.
* Soft delete recommended for tasks (optional).

---

# 6️⃣ Functional Requirements (API Level)

## User APIs

``` text
POST   /users
GET    /users
GET    /users/{id}
```

---

## Team APIs

``` text
POST   /teams
POST   /teams/{teamId}/members/{userId}
GET    /teams
GET    /teams/{teamId}
GET    /teams/{teamId}/projects
```

---

## Project APIs

``` text
POST   /teams/{teamId}/projects
GET    /projects/{id}
GET    /projects?teamId=1
DELETE /projects/{id}
```

---

## Task APIs

``` text
POST   /projects/{projectId}/tasks
GET    /tasks/{id}
GET    /tasks?projectId=1
PATCH  /tasks/{id}/status
DELETE /tasks/{id}
```

---

## Reviewer APIs

``` text
POST   /tasks/{taskId}/assign-reviewers
POST   /tasks/{taskId}/review
GET    /tasks/{taskId}/reviews
```

---

## Dashboard APIs

``` text
GET /teams/{teamId}/dashboard
```

Returns:

* Total projects
* Total tasks
* Tasks by status
* Pending reviews count
* Overdue tasks count

---

# 7️⃣ Non-Functional Requirements

* RESTful design
* Proper HTTP status codes
* DTO layer (no direct entity exposure)
* Global exception handling
* Pagination support
* Validation using annotations
* Clean layered architecture

---

# 8️⃣ Architecture Deliverables

Your backend must contain:

``` text
controller/
service/
repository/
entity/
dto/
exception/
```

Must include:

* Custom exceptions
* Service-layer validation
* Unit-testable service logic
* Proper JPA mappings
* Enum usage for status/role

---

# 9️⃣ Edge Cases You Must Handle

* Reviewer tries to review twice
* Non-team member tries to access task
* Task marked DONE without reviewers
* Assigned user removed from team
* Reviewer removed before review
