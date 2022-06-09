describe('My First Test', () => {
  it('Visits the initial project page', () => {
    cy.visit('/')
    cy.contains('Welcome')
    cy.contains('bookstore')
    cy.contains('Login')
  })
})


it("Resgister new User", function () {
  cy.visit("http://localhost:4200/signup")
  cy.get('mat-form-field[id="name"]').type('john')
  cy.get('mat-form-field[id="username"]').type('j')
  cy.get('mat-form-field[id="password"]').type('123')
  cy.get('button[id="signupbutton"]')
})


describe('Login Test', () => {
  it('Visits the initial project page', () => {
    cy.visit('/')
    cy.contains('Login')
    cy.get('input[name="username"]').type('user')
    cy.get('input[name="password"]').type('user')
    cy.get('button[id="login"]').click()
  })
})

it("Visit the cart page", function () {
  cy.visit('/cart')
})

describe('3rd Test', () => {
  it("checkout test", function () {
    cy.visit("http://localhost:4200/adddeleteupdate")
    cy.contains("List of Available Books")
  })
})
