import newPost from '../fixtures/newPost.json';

describe('BlogPost Tests', () => {
    before(() => {
        cy.login();
    })

    beforeEach(() => {});

    afterEach(() => {});

    it('Create New BlogPost', () => {
        cy.getElement("header-create-post").click();
        cy.getElement("createPost-title").type(newPost.title);
        cy.getElement("createPost-text").type(newPost.text);
        cy.getElement("createPost-category").type(newPost.category);
        cy.getElement("createPost-submit").click();

        cy.getElement(newPost.title);
    });

    it('Delete New BlogPost', () => {
        let amount = cy.getElement(newPost.title + "-delete", false).length;

        cy.getElement(newPost.title + "-delete", false).click();

        cy.getElement(newPost.title + "-delete", false).to.have.length(amount - 1);
    })
})