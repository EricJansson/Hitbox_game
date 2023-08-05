
# Game project

## Code

### GameObjects.Entity.obstacleCheck()

#### Description

This function will check for collision with all obstacles 
in the map and push the entity out of collision.

It will
* check whether the collision should be on the side or above/under
the obstacle,
* adapt to multiple obstacle collisions at once,
* adapt to the obstacle will the largest collision area first to ensure
correct adaptation when colliding with multiple sources,
 
The function checks the ratio between the velocity vector
and the deltaX/deltaY values. Lower ratio = prio(?)