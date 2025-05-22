To create a simple CSS Grid with dynamic rows and columns, you can use the `display: grid` property on the container element and then define the column widths using `grid-template-columns`. The number of rows will be automatically created based on the number of child elements. [1, 1, 2]  

Here's a basic example: 

```html
<!DOCTYPE html>
<html>
<head>
<title>CSS Grid</title>
<style>
.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr); /* Three equal columns */
  gap: 10px; /* Optional: Add some space between grid items */
}

.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.2);
  padding: 20px;
  text-align: center;
  font-size: 18px;
}
</style>
</head>
<body>

<div class="grid-container">
  <div class="grid-item">1</div>
  <div class="grid-item">2</div>
  <div class="grid-item">3</div>
  <div class="grid-item">4</div>
  <div class="grid-item">5</div>
  <div class="grid-item">6</div>
</div>

</body>
</html>
```

In this example: 

• The `.grid-container` element becomes a grid container because `display: grid` is set. [2, 3, 4]  
• `grid-template-columns: repeat(3, 1fr)` defines three columns of equal width, using the `1fr` unit to divide the available space equally. [3, 3]  
• The `.grid-item` elements are the children of the grid container and will be placed automatically within the grid. [1, 2, 5]  
• The grid automatically creates as many rows as needed to accommodate all the grid items. [1, 1]  


[1] https://www.joshwcomeau.com/css/interactive-guide-to-grid/[2] https://css-tricks.com/snippets/css/complete-guide-grid/[3] https://stackoverflow.com/questions/48665316/how-to-create-a-css-grid-with-3-columns-and-unlimited-amount-of-items[4] https://www.w3schools.com/css/css_grid.asp[5] https://developer.mozilla.org/en-US/docs/Web/CSS/grid-column[-] https://blog.devgenius.io/css-daily-tips-3-master-flexbox-in-5-minutes-ee130cba5e60[-] https://docs.pingcode.com/baike/2962041
Not all images can be exported from Search.
